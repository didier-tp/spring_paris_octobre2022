package tp.appliSpring.util_dto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.dto.CompteDetaille;
import tp.appliSpring.dto.CompteEssentiel;
import tp.appliSpring.dto.OperationEssentiel;

public class DtoConverter {
	
	/*
	public static CompteEssentiel compteToCompteEssentiel(Compte compte) {
		return new CompteEssentiel(compte.getNumero(),compte.getLabel(),compte.getSolde());
	}
	*/
	
	/*
	 * NB: il existe des api complementaires spécialisées dans les conversions d'objets java (DTO ou ...)
	 * MapStruct : bonnes performances , Dozer : trop lent , ....
	 */
	
	public static CompteEssentiel compteToCompteEssentiel(Compte compte) {
		CompteEssentiel compteEssentiel= new CompteEssentiel();
		/*
		compteEssentiel.setNumero(compte.getNumero());
		compteEssentiel.setLabel(compte.getLabel());
		compteEssentiel.setSolde(compte.getSolde());
		*/
		BeanUtils.copyProperties(compte, compteEssentiel);//recopie de source vers destination 
		                                               //toutes les propriétés/attributs de mêmes noms
		return compteEssentiel;
	}
	
	public static OperationEssentiel operationToOperationEssentiel(Operation op) {
		OperationEssentiel operationEss= new OperationEssentiel();
		BeanUtils.copyProperties(op, operationEss);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		operationEss.setDateOp(sdf.format(op.getDateOp()));
		return operationEss;
	}
	
	
	public static CompteDetaille compteToCompteDetaille(Compte compte) {
		CompteDetaille compteDetaille= new CompteDetaille();
		BeanUtils.copyProperties(compte, compteDetaille);
		compteDetaille.setOperations(
				compte.getOperations().stream()
				.map(op -> operationToOperationEssentiel(op))
				.collect(Collectors.toList())
				);
		return compteDetaille;
	}
	
	public static Compte compteEssentielToCompte(CompteEssentiel compteEssentiel) {
		Compte compte= new Compte();
		BeanUtils.copyProperties(compteEssentiel, compte);
		return compte;
	}

	public static List<CompteEssentiel> comptesToComptesEssentiels(List<Compte> comptes){
		/*
		List<CompteEssentiel> comptesEssentiels = new ArrayList<>();
		for(Compte compte : comptes) {
			comptesEssentiels.add(compteToCompteEssentiel(compte));
		}
		return comptesEssentiels;
		*/
		return comptes.stream()
				.map(compte -> compteToCompteEssentiel(compte))
				.collect(Collectors.toList());
	}
}
