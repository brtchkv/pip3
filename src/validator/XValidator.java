package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("XValidator")
public class XValidator implements Validator {
    private static final double[] possibleX = {-2, -1, 0, 1, 2};

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double x = Double.parseDouble(o.toString().replace(',', '.'));
            if (!matchX(x))
                throw new IllegalArgumentException();
        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("X validation failed.",
                            "Неверная координата Х.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    private static boolean matchX(double x) {
        for (double aPossibleX : possibleX)
            if (x == aPossibleX)
                return true;
        return false;
    }
}
