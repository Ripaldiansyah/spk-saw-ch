package chloro.UI.TextField;

import javax.swing.Icon;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatTextField;

import java.awt.*;

public class TextFieldCustom extends FlatTextField {

    public TextFieldCustom(
            String placeholder,
            Icon icon,
            boolean isLeadingIcon)

    {
        setPlaceholderText(placeholder);
        if (isLeadingIcon) {
            setLeadingIcon(icon);
        } else {
            setTrailingIcon(icon);
        }
        setShowClearButton(true);
        setPreferredSize(new Dimension(getPreferredSize().width, 45));
        setSelectAllOnFocusPolicy(SelectAllOnFocusPolicy.always);
        putClientProperty(FlatClientProperties.STYLE, ""
                + "focusColor:#8490fb;"
                + "borderWidth:1;"
                + "arc:20;"
                + "focusedBorderColor:#8490fb");

    }

    public FlatTextField getTextField() {
        return this;
    }

}
