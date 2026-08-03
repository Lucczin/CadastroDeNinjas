package dev.java10x.CadastroDeNinja.Ninjas;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasvindas(){
        return "Essa e minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja ){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: "+novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostra todos os Ninjas(READ)
    @GetMapping("/listar")
    public ResponseEntity <List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas() ;
        return ResponseEntity.ok(ninjas);
    }

    // Mostra ninja por ID(READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listaNinjasPorID(@PathVariable Long id){

       NinjaDTO ninja = ninjaService.listaNinjaPorID(id);
       if (ninja !=null){
            return ResponseEntity.ok(ninja);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja com o ID: " + id +" não existe no nossos registros");
       }
    }

    // Altera dados dos ninjas(UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id,@RequestBody NinjaDTO ninjaAtualizado) {

        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja !=null){
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID:" + id +" não foi encontrado");
        }
    }
    // Deletar Ninjas (Delate)
   @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id){

        if (ninjaService.listaNinjaPorID(id) !=null ){
            ninjaService.deletarNinjaPorID(id);
            return ResponseEntity.ok("Ninja com o ID " +id + " deletado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com o ID: " + id + " não foi encontrado" );
        }

   }


}
