package dev.java10x.CadastroDeNinja.Ninjas;

import dev.java10x.CadastroDeNinja.Missoes.MissoesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel,Long> {
}
