package com.example.prueba.controller;

import com.example.prueba.model.*;
import com.example.prueba.paypal.PaypalService;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.cliente.ClienteService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.producto.ProductoService;
import com.example.prueba.services.provincia.ProvinciaService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CarritoController {
    @Autowired
    private ProductoService servicio;
    @Autowired
    private PedidoService servicePedido;

    @Autowired
    private CategoriaService serviceCategoria;
    @Autowired
    private LineaPedidoService serviceLineaPedido;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private PaypalService paypalService;


    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping({"/tienda"})
    public String listarUsu(Model model) {
        model.addAttribute("listaProductosTienda", servicio.findAll());
        return "tienda";
    }

    @GetMapping("/categoria/{idCategoria}")
    public String listarCategoria(Model model, @PathVariable int idCategoria) {
        Categoria categoria = serviceCategoria.findById(idCategoria);

        model.addAttribute("listaProductos", servicio.selectProducto(idCategoria));
        model.addAttribute("listaCategoria", categoria);
        return "tiendaCategoria";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto) {

        LineaPedido lineaPedido = new LineaPedido();

        Producto producto = servicio.findById(idProducto);
        model.addAttribute("lineaPedido", lineaPedido);
        model.addAttribute("listaProductosTienda", producto);
        return "productos";
    }

    @PostMapping({"/carrito/{idProducto}"})
    public String carrito(@ModelAttribute("lineaPedido") LineaPedido lineaPedido, Pedido pedido, @PathVariable int idProducto) {
        pedido.setSesionID(servicePedido.obtenerID());

        Pedido pedido1 = servicePedido.selectPedido(pedido.getSesionID());

        if (pedido1 == null || pedido1.isConfir()) {
            servicePedido.add(pedido);
            lineaPedido.setPedido(pedido);
        } else {
            servicePedido.edit(pedido1);
            lineaPedido.setPedido(pedido1);
        }

        Producto producto = servicio.findById(idProducto);
        lineaPedido.setProducto(producto);

        LineaPedido linea = serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto());

        if (linea == null) {

            lineaPedido.setSubtotal(lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad());
            lineaPedido.setPtotal(lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad());
            calcularEnvio(lineaPedido);

            serviceLineaPedido.add(lineaPedido);
            recorrerCarrito(lineaPedido);

        } else {
            linea.setCantidad(linea.getCantidad() + lineaPedido.getCantidad());

            linea.setSubtotal(linea.getSubtotal() + (lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad()));
            linea.setPtotal(linea.getPtotal() + (lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad()));
            calcularEnvio(linea);

            serviceLineaPedido.edit(linea);
            recorrerCarrito(linea);
        }
        return "redirect:/tienda";
    }

    @GetMapping({"/carrito"})
    public String verCarrito(Model model, LineaPedido lineaPedido) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        lineaPedido.setPedido(pedido1);

        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(pedido1.getIdPedido());

        model.addAttribute("lineasCarrito", lineaPedido1);
        model.addAttribute("listCategorias", serviceCategoria.findAll());
        if (lineaPedido1.size() == 0) {
            pedido1.setpEnvio(0);
            pedido1.setTotalPedido(0);
            model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
            model.addAttribute("precioEnvio", pedido1.getpEnvio());
        }
        model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
        model.addAttribute("precioEnvio", pedido1.getpEnvio());
        return "carrito";
    }

    @GetMapping("/carrito/borrar/{id}")
    public String borrarProductoForm(@PathVariable int id) {
        LineaPedido lineaPedido = serviceLineaPedido.findById(id);
        if (lineaPedido != null) {
            serviceLineaPedido.delete(lineaPedido);
            recorrerCarrito(lineaPedido);
            return "redirect:/carrito";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping({"/cliente/facturacion"})
    public String clienteNewForm(Model model, LineaPedido lineaPedido, PayPal payPal) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        lineaPedido.setPedido(pedido1);

        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(pedido1.getIdPedido());

        payPal.setCurrency("EUR");
        payPal.setMethod("paypal");
        payPal.setIntent("sale");

        model.addAttribute("price", pedido1.getTotalPedido());
        model.addAttribute("currency", payPal.getCurrency());
        model.addAttribute("method", payPal.getMethod());
        model.addAttribute("intent", payPal.getIntent());


        //CONFIRMAT
        model.addAttribute("clienteAceptar", new Cliente());
        model.addAttribute("provincias", provinciaService.findAll());
        model.addAttribute("lineasCarrito", lineaPedido1);
        model.addAttribute("totalCarrito", lineaPedido.getPedido().getTotalPedido());
        model.addAttribute("precioEnvio", pedido1.getpEnvio());

        return "clienteFacturacion";
    }

    @PostMapping({"/pay"})
    public String nuevoclienteSubmit(@ModelAttribute("clienteAceptar") Cliente cliente, PayPal payPal) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        Cliente cliente1 = clienteService.getCliente(cliente.getEmail());

        //creo el cliente si no existe
        if (cliente1 == null) {
            clienteService.add(cliente);
            pedido1.setCliente(cliente);
        } else {
            //añadir al pedido el cliente
            pedido1.setCliente(cliente1);
        }
        //agrego la fecha actual al pedido
        pedido1.setFecha(String.valueOf(LocalDate.now()));

        /**
         * La parte de Paypal
         */
        try {
            Payment payment = paypalService.createPayment(pedido1.getTotalPedido(), payPal.getCurrency(), payPal.getMethod(),
                    payPal.getIntent(), "http://localhost:9000/" + CANCEL_URL,
                    "http://localhost:9000/" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }

        return "redirect:/";
    }

    /*   @GetMapping({"/pagarTarjeta"})
       public String pagarTarjeta(Model model, PayPal payPal) {
           Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
           payPal.setCurrency("EUR");
           payPal.setMethod("paypal");
           payPal.setIntent("sale");

           model.addAttribute("price", pedido1.getTotalPedido());
           model.addAttribute("currency", payPal.getCurrency());
           model.addAttribute("method", payPal.getMethod());
           model.addAttribute("intent", payPal.getIntent());
          return "pagar";
       } */
 /*   @PostMapping("/pay")
    public String payment(@ModelAttribute("payment") PayPal payPal) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        try {
            Payment payment = paypalService.createPayment(pedido1.getTotalPedido(), payPal.getCurrency(), payPal.getMethod(),
                    payPal.getIntent(), "http://localhost:9000/" + CANCEL_URL,
                    "http://localhost:9000/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }*/
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        Pedido pedido1 = servicePedido.selectPedido(servicePedido.obtenerID());
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
               /* ScriptEngineManager manager =new ScriptEngineManager();
                ScriptEngine script = manager.getEngineByName("javascript");
                try {
                    script.eval("alert('Pedido confirmado')");
                } catch (ScriptException e) {
                    throw new RuntimeException(e);
                }*/
                /**
                 * una vez el cliente ha pagado correctamente, el pedido pasará a estar confirmado.
                 */
                pedido1.setConfir(true);
                return "/index";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }


    public void recorrerCarrito(LineaPedido linea) {
        double totalCarrito = 0;
        List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(linea.getPedido().getIdPedido());

        for (LineaPedido lineaPedido : lineaPedido1) {
            totalCarrito = totalCarrito + lineaPedido.getSubtotal();
        }

        linea.getPedido().setTotalPedido(totalCarrito + linea.getPedido().getpEnvio());
    }

    public void calcularEnvio(@ModelAttribute("lineaPedido") LineaPedido linea) {

        if (linea.getPtotal() < 3) {
            linea.getPedido().setpEnvio(8);
        } else if (linea.getPtotal() >= 3 && linea.getPtotal() < 6) {
            linea.getPedido().setpEnvio(10);
        } else if (linea.getPtotal() >= 6 && linea.getPtotal() < 10) {
            linea.getPedido().setpEnvio(12);
        } else {
            linea.getPedido().setpEnvio(18);
        }
    }

}
