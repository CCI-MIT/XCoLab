package com.ext.utils.userInput;

public interface UserInputFilter {
    String filterHtml(String html) throws UserInputException;
}
