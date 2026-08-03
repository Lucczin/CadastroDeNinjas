package dev.java10x.CadastroDeNinja.Ninjas;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControlleUi {

    private final NinjaService ninjaService;

    public NinjaControlleUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorID(@PathVariable Long id) {
        ninjaService.deletarNinjaPorID(id);
        return "redirect:/ninjas/ui/listar";
    }


    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhes(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listaNinjaPorID(id);
        model.addAttribute("ninja", ninja);
        return "detalhesninjas"; // deve bater com o nome do arquivo
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja"; // substitua pelo nome correto da sua view (ex: HTML/Thymeleaf)
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes){
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem","Ninja Adicionado com sucesso");
        return "redirect:/ninjas/ui/listar";
    }
    // Exibir formulário de edição
    @GetMapping("/alterar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listaNinjaPorID(id);
        if (ninja == null) {
            return "redirect:/erro"; // ou algum tratamento de erro
        }
        model.addAttribute("ninja", ninja);
        return "editarNinja";
    }
}




