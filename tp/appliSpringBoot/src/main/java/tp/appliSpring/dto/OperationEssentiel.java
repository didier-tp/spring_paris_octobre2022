package tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter //ou bien @Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationEssentiel {
	
	private Long numOp;
	private Double montant;
	private String label;
	private String dateOp; /* String plutot que Date */
    //pas le lien avec le compte 
}
