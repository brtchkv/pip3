package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("rCheck")
public class RValidator implements Validator {
    private static final double[] possibleR = {1.5, 1, 2, 2.5, 3, 3.5};

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double r = Double.parseDouble(o.toString().replace(',', '.'));
            if (!matchR(r))
                throw new IllegalArgumentException();
        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("R validation failed.",
                            "Неверный параметр R.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    private static boolean matchR(double r) {
        for (double aPossibleR : possibleR)
            if (r == aPossibleR)
                return true;
        return false;
    }
}