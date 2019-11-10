package validator;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("XValidator")
public class XValidator implements Validator {
    private static final double minX = -2;
    private static final double maxX = 2;

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {

            double x = Double.parseDouble(o.toString().replace(',', '.'));
            if (!(x >= minX && x <= maxX))
                throw new IllegalArgumentException();

        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("X validation failed.",
                            "Неверная координата Х.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
