package com.bolsadeideas.springboot.app.controller;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.app.model.service.IClienteService;
import com.bolsadeideas.springboot.app.models.entity.Cliente;



@Controller
public class ClienteController {

	@Autowired
	private IClienteService clienteServices;
	
	//@GetMapping("/listar")
	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de cliente");
		model.addAttribute("clientes",clienteServices.findAll());
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
	public String editar(@PathVariable(value="id")Long id, Map<String,Object> model) {
		Cliente cliente = null;
		if(id>0) {
			cliente = clienteServices.findOne(id);
		}
		else {
			return "redirect:/listar";
		}
		
		model.put("titulo", "Editar cliente");
		model.put("cliente",cliente);
		return "form";
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		
		System.out.println("Errores:"+result.hasErrors());
		System.out.println("nombrecliente:"+"-"+cliente.getNombre()+"-");
		if(result.hasErrors()) {

			model.addAttribute("titulo", "Agregar cliente2 dio error");
			return "form";
		}
		//model.addAttribute("titulo", "Agregar cliente");
		
		
		clienteServices.save(cliente);
		
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id, Map<String,Object> model) {
	
		if(id>0) {
			clienteServices.delete(id);
		}
		
			return "redirect:/listar";
	
		
	}
}
