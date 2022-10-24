package tp.appliSpring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter //ou bien @Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompteDetaille extends CompteEssentiel {
	
	private List<OperationEssentiel> operations = new ArrayList<>();

}
