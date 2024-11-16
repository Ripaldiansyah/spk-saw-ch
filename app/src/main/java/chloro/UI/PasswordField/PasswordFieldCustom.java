package chloro.UI.PasswordField;

import javax.swing.Icon;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatPasswordField;
import com.formdev.flatlaf.extras.components.FlatTextField.SelectAllOnFocusPolicy;

import java.awt.*;

public class PasswordFieldCustom extends FlatPasswordField {

    public PasswordFieldCustom(
            String placeholder,
            Icon icon,
            boolean isLeadingIcon)

    {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "focusColor:#8490fb;"
                + "borderWidth:1;"
                + "arc:20;"
                + "focusedBorderColor:#8490fb");
        setPlaceholderText(placeholder);
        if (isLeadingIcon) {
            setLeadingIcon(icon);
        } else {
            setTrailingIcon(icon);
        }
        setPreferredSize(new Dimension(getPreferredSize().width, 45));
        setShowClearButton(true);
        setSelectAllOnFocusPolicy(SelectAllOnFocusPolicy.always);

    }

    public FlatPasswordField getPasswordField() {
        return this;
    }

}
