package movies;

import java.io.Serializable;

public class IdGenerator {

    public static String generate(Product product) {
    	StringBuffer result = new StringBuffer();
    	if (product instanceof Movie) {
    		result.append("MOV");
    	} else if (product instanceof Game) {
    		result.append("GAM");
    	} 
    	else result.append("BOO");
    	result.append(System.identityHashCode(product));
	
	return result.toString();
    }
}