package main.java.validator;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("RValidator")
public class RValidator implements Validator {
    private static final double[] possibleR = {1.5, 1, 2, 2.5, 3};

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double r = Double.parseDouble(o.toString().replace(',', '.'));
            if (!matchR(r))
                throw new IllegalArgumentException();
        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("R validation failed.",
                            "\u041D\u0435\u0432\u0435\u0440\u043D\u0430\u044F \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 R."
                    );
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            RequestContext.getCurrentInstance().showMessageInDialog(msg);
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