/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.antego.authorreg;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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