package main.java.validator;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("UValidator")
public class YValidator implements Validator {
    private static final double minY = -5;
    private static final double maxY = 5;

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double y = Double.parseDouble(o.toString().replace(',', '.'));
            if (!(y >= minY && y <= maxY))
                throw new IllegalArgumentException();
        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("Y validation failed.",
                            "\u041D\u0435\u0432\u0435\u0440\u043D\u0430\u044F \20\u043A\u043E\u043E\u0440\u0434\u0438\u043D\u0430\u0442\u0430\20 Y."
                    );
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            RequestContext.getCurrentInstance().showMessageInDialog(msg);
            throw new ValidatorException(msg);
        }
    }
}
