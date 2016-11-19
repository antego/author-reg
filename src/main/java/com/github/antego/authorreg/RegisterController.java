package com.github.antego.authorreg;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// todo error page
// todo test
@Controller
public class RegisterController {
    @Autowired
    private EMAService emaService;

    @Autowired
    private NamedAccountSerializer serializerService;

    @GetMapping("/")
    public String registerForm(Model model) {
        model.addAttribute("copyrightHolder", new CopyrightHolder());
        return "register";
    }

    @PostMapping("/")
    public HttpEntity<byte[]> registerSubmit(@ModelAttribute CopyrightHolder copyrightHolder) throws JsonProcessingException {
        NamedAccount account = emaService.getNamedAccount(copyrightHolder.getName());
        byte[] serializedNamedAccount = serializerService.serialize(account);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json"));
        header.set("Content-Disposition",
                "attachment; filename=keys.json");
        header.setContentLength(serializedNamedAccount.length);

        return new HttpEntity<>(serializedNamedAccount, header);
    }
}