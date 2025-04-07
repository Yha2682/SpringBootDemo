package com.example.springbootdemo.validation;
import com.example.springbootdemo.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StateValidation implements ConstraintValidator<State, String/*<给哪个注解提供校验规则，校验的数据类型>*/> {
    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext
     * @return 如果返回的是false 则校验不通过 true则通过
     */

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null) {
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿")) {
            return true;
        }
        return false;
    }
}
