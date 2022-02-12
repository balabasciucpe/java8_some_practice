package com.modernjava.cap8_Streams;

public class StrategyLambda {

    public static void main(String[] args) {

        //old way
        Validator validator1 = new Validator(new IsNumeric());
        System.out.println(validator1.validate("afvc"));
        Validator validator2 = new Validator(new IsAllLowerCase());
        System.out.println(validator2.validate("sfasdfass"));

        //lambda way
        Validator lambdaValidator = new Validator((String s) -> s.matches("\\d+"));
        System.out.println(lambdaValidator.validate("2341"));
        Validator lambdaValidator2 = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(lambdaValidator2.validate("FDSFD"));

    }

    interface ValidatorStrategy
    {
        public boolean execute(String s);
    }

    static private class  IsAllLowerCase implements ValidatorStrategy
    {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    static private class IsNumeric implements ValidatorStrategy
    {
        @Override
        public boolean execute(String s) {
            return s.matches("[\\d+]");
        }
    }

    static private class Validator
    {
        private final ValidatorStrategy validatorStrategy;

        public Validator(ValidatorStrategy validatorStrategy) {
            this.validatorStrategy = validatorStrategy;
        }

        public boolean validate(String s)
        {
           return validatorStrategy.execute(s);
        }
    }

}
