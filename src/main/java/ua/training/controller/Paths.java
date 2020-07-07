package ua.training.controller;

import ua.training.model.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Paths {
    String getPath();
    void go(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
