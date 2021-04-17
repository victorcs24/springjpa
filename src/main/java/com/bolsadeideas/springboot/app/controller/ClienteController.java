package com.bolsadeideas.springboot.app.controller;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.model.service.IClienteService;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;



@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteServices;
	
	//@GetMapping("/listar")
	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue = "0") int page,   Model model) {
		
		Pageable pageRequest=PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteServices.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes); 
		model.addAttribute("titulo", "Lista de cliente");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page",pageRender);
		//model.addAttribute("nombreB","");
		//model.addAttribute("clientes",clienteServices.findByNombreLike("e1"));
		return "listar";
	}
	
	@RequestMapping(value = "/buscar",method = RequestMethod.POST)
	public String buscar(@RequestParam("nombreB") String nombreB,Model model) {
		model.addAttribute("titulo", "Buscar cliente");
		model.addAttribute("clientes",clienteServices.findAll());
		//model.addAttribute("nombreB","");
		//String nombreB = (String)request.getAttribute("nombreB");
		System.out.println("nombreB:"+nombreB);
		model.addAttribute("nombreB", nombreB);
		
		model.addAttribute("clientes",clienteServices.findByNombreLike(nombreB));
		return "listar";
	}
	
	@RequestMapping(value = "/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Agregar cliente");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value="id")Long id, Map<String,Object> model,RedirectAttributes flash) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteServices.findOne(id);
			if(cliente==null) {
				flash.addFlashAttribute("error", "El cliente no existe en BD!!!!");
				return "redirect:/listar";
			}
		}
		else {
			flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
			return "redirect:/listar";
		}
		
		model.put("titulo", "Editar cliente");
		model.put("cliente",cliente);
		return "form";
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash) {
		
		System.out.println("Errores:"+result.hasErrors());
		System.out.println("nombrecliente:"+"-"+cliente.getNombre()+"-");
		if(result.hasErrors()) {

			model.addAttribute("titulo", "Agregar cliente2 dio error");
			return "form";
		}
		//model.addAttribute("titulo", "Agregar cliente");
		
		flash.addFlashAttribute("success", "Cliente agregado con exito");
		clienteServices.save(cliente);
		
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id, Map<String,Object> model,RedirectAttributes flash) {
	
		if(id>0) {
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
			clienteServices.delete(id);
		}
		
			return "redirect:/listar";
	
		
	}
}
