package tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * DRY : Don't Repeat Yoursef
 * KISS : Keep It Simple Stupid
 * --------
 * KISS or SLAP
 */

//CompteEssentiel = une version simple de DTO
//DTO = Data Transfert Object (objet de données transféréré d'une couche logicielle à une autre 
//ou bien d'une application à une autre )
@Getter @Setter //ou bien @Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompteEssentiel {
    private Long numero;
    private String label;
    private Double solde;
    
}
