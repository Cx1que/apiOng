package br.com.unifecaf.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unifecaf.service.entity.Cachorro;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long> {

}
