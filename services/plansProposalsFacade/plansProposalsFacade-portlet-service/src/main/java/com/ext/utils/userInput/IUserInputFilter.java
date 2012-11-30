package com.ext.utils.userInput;

public interface IUserInputFilter {
    String filterHtml(String html) throws UserInputException;
}
