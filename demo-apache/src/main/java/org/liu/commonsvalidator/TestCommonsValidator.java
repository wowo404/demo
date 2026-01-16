package org.liu.commonsvalidator;

import org.apache.commons.validator.*;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.liu.model.Animal;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TestCommonsValidator {

    public static void main(String[] args) throws CheckDigitException {
        TestCommonsValidator testCommonsValidator = new TestCommonsValidator();
        testCommonsValidator.test3();
    }

    public void test3() throws CheckDigitException {
        String num = "890746352";
        String checkDigit = LuhnCheckDigit.LUHN_CHECK_DIGIT.calculate(num);
        System.out.println(checkDigit);
        boolean valid = LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(num + checkDigit);
        System.out.println(valid);
    }

    public void test2() {
        boolean isUrl = GenericValidator.isUrl("http://www.google.com");
        System.out.println(isUrl);
    }

    /**
     * 通过xml文件的配置方式，来校验bean
     * https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/package-summary.html#package_description
     */
    public void test1() throws ValidatorException, IOException, SAXException {
        InputStream in = this.getClass().getResourceAsStream("validator-name-required.xml");
// Create an instance of ValidatorResources to initialize from an xml file.
        ValidatorResources resources = new ValidatorResources(in);
// Create bean to run test on.
        Animal name = new Animal();//这是自定义的一个bean
// Construct validator based on the loaded resources and the form key
        Validator validator = new Validator(resources, "nameForm");
// add the name bean to the validator as a resource
// for the validations to be performed on.
        validator.setParameter(Validator.BEAN_PARAM, name);
// Get results of the validation.
        ValidatorResults validatorResults;
// throws ValidatorException (catch clause not shown here)
        validatorResults = validator.validate();
        Map<String, Object> results = validatorResults.getResultValueMap();
        if (results.get("firstName") == null) {
// no error
        } else {
// number of errors for first name
            int errors = ((Integer) results.get("firstName")).intValue();
        }
    }

}
