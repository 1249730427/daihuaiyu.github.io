package com.daihuaiyu.secondskill.validator;

import com.daihuaiyu.secondskill.util.ValidatorMobileUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证手机号
 *
 * @author daihuaiyu
 * @create: 2021-05-10 17:13
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean require =false;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        this.require = constraintAnnotation.required();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(require){
            return ValidatorMobileUtil.require(value);
        }else{
            if(value==null || StringUtils.isEmpty(value)){
                return true;
            }else{
                return ValidatorMobileUtil.require(value);
            }
        }
    }
}

