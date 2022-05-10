package com.example.prueba.controller;

import com.example.prueba.constantes.Constante;
import com.example.prueba.model.Categoria;
import com.example.prueba.model.LineaPedido;
import com.example.prueba.model.Pedido;
import com.example.prueba.model.Producto;
import com.example.prueba.services.categoria.CategoriaService;
import com.example.prueba.services.lineapedido.LineaPedidoService;
import com.example.prueba.services.pedido.PedidoService;
import com.example.prueba.services.producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.NumberFormat;
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

    @GetMapping({"/tienda"})
    public String listarUsu(Model model){
        model.addAttribute("listaProductosTienda", servicio.findAll()) ; // inyecta el servicio gracias al @Autowired anterior
        return "tienda";
    }
    @GetMapping("/categoria/{idCategoria}")
    public String listarCategoria(Model model, @PathVariable int idCategoria){
        Categoria categoria=serviceCategoria.findById(idCategoria);

        model.addAttribute("listaProductos",servicio.selectProducto(idCategoria));
        model.addAttribute("listaCategoria",categoria);
        return "tiendaCategoria";
    }

    @GetMapping({"/productos/{idProducto}"})
    public String listarDescripcion(Model model, @PathVariable int idProducto){

        LineaPedido lineaPedido= new LineaPedido();

        Producto producto=servicio.findById(idProducto);
        model.addAttribute("lineaPedido", lineaPedido);
        model.addAttribute("listaProductosTienda", producto) ;// inyecta el servicio gracias al @Autowired anterior

        return "productos";
    }
    @PostMapping({"/carrito/{idProducto}"})
    public String carrito(@ModelAttribute("lineaPedido") LineaPedido lineaPedido , Pedido pedido,  @PathVariable int idProducto){
        String session_id = servicePedido.obtenerID();
        Constante.SESSION_ID = session_id;
        pedido.setSesionID(session_id);
       Pedido pedido1 = servicePedido.selectPedido( Constante.SESSION_ID);

       if (pedido1 ==null){
           servicePedido.add(pedido);
           lineaPedido.setPedido(pedido);
       }else {
           servicePedido.edit(pedido1);
           lineaPedido.setPedido(pedido1);

       }

        Producto producto=servicio.findById(idProducto);
        lineaPedido.setProducto(producto);

       // List<LineaPedido> linea =serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto(),lineaPedido.getPedido().getIdPedido());
        LineaPedido linea =serviceLineaPedido.loginByProducto(lineaPedido.getProducto().getIdProducto()); //agregar una listA?????????

        //siempre es null
        if (linea ==null ){

            lineaPedido.setSubtotal(lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad());
            lineaPedido.setPtotal(lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad());
            calcularEnvio(lineaPedido);
   /*         double res = lineaPedido.getSubtotal() +  lineaPedido.getPedido().getpEnvio();
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
            nf.format(res);
            lineaPedido.getPedido().setTotalPedido(res);*/

            serviceLineaPedido.add(lineaPedido);
                /*    for (int i=0; i <lineaPedido1.size(); i++) {
            lineaPedido.getPedido().setTotalPedido(lineaPedido1.get(i).getPedido().getTotalPedido());
          //  totalCarrito = totalCarrito + lineaPedido1.get(i).getPedido().getTotalPedido();
           // precioEnvio = lineaPedido1.get(i).getPenvio();

        }*/
            lineaPedido.getPedido().setTotalPedido(lineaPedido.getSubtotal() + lineaPedido.getPedido().getpEnvio());

        }else {
            //Arreglar

           linea.setCantidad(linea.getCantidad() + lineaPedido.getCantidad());

           linea.setSubtotal(linea.getSubtotal() + (lineaPedido.getProducto().getPrecio() * lineaPedido.getCantidad()));
            linea.setPtotal(linea.getPtotal() + (lineaPedido.getProducto().getPeso() * lineaPedido.getCantidad()));
            calcularEnvio(linea);
            linea.getPedido().setTotalPedido( linea.getSubtotal() + lineaPedido.getPedido().getpEnvio());

            //double res2 =linea.getSubtotal() + lineaPedido.getPedido().getpEnvio();
           /* NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
            nf.format(res2);
            linea.getPedido().setTotalPedido(res2);*/

            serviceLineaPedido.edit(linea);
        }
        return "redirect:/tienda";
    }

    public void calcularEnvio(@ModelAttribute("lineaPedido") LineaPedido linea) {

        if (linea.getPtotal() <3){
            linea.getPedido().setpEnvio(8);
        }else if (linea.getPtotal() >=3 && linea.getPtotal() <6){
            linea.getPedido().setpEnvio(10);
        }else if (linea.getPtotal() >=6 && linea.getPtotal() <10){
            linea.getPedido().setpEnvio(12);
        }else{
            linea.getPedido().setpEnvio(18);
        }
    }

    @GetMapping({"/carrito"})
    public String verCarrito(Model model, LineaPedido lineaPedido,Pedido pedido){
        String session_id = servicePedido.obtenerID();
        Constante.SESSION_ID = session_id;
        pedido.setSesionID(session_id);
        Pedido pedido1 = servicePedido.selectPedido(Constante.SESSION_ID);
        lineaPedido.setPedido(pedido1);

       List<LineaPedido> lineaPedido1 = serviceLineaPedido.selectLineas(pedido1.getIdPedido());
        //for (LineaPedido i : lineaPedido1) totalCarrito = totalCarrito + i.getTotal();
    /*    for (int i=0; i <lineaPedido1.size(); i++) {
            lineaPedido.getPedido().setTotalPedido(lineaPedido1.get(i).getPedido().getTotalPedido());
          //  totalCarrito = totalCarrito + lineaPedido1.get(i).getPedido().getTotalPedido();
           // precioEnvio = lineaPedido1.get(i).getPenvio();

        }*/
        //totalCarrito = lineaPedido.getPedido().getTotalPedido();
        model.addAttribute("lineasCarrito",lineaPedido1);
        model.addAttribute("listCategorias",serviceCategoria.findAll());
        model.addAttribute("totalCarrito",lineaPedido.getPedido().getTotalPedido());
        model.addAttribute("precioEnvio", pedido1.getpEnvio());
        return "carrito";
    }
/*    public calcularEnvio(){

    }*/
}
