package validate;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class BValidator extends FieldValidatorSupport {

	private String b;
	@Override
	public void validate(Object object) throws ValidationException {
		//获取字段名
		String fieldName = super.getFieldName();
		String value = (String)super.getFieldValue(fieldName,object);
		System.out.println("自定义validator"+fieldName+","+value);
		if(value == null || value == "") {
			//message
			this.addFieldError(fieldName, object);
			//fielderror
			this.getValidatorContext().addFieldError(fieldName, this.getValidatorContext().getText(value+"b为空"+b));
			return;
		}
	}

}
