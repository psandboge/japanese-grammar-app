package se.sandboge.grammarapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.sandboge.japanese.conjugation.Verb;

@Controller
public class GrammarWeb {

    @RequestMapping("/grammar")
    public String grammar(
            @RequestParam(value="class", required=false, defaultValue="Verb") String clazz,
            @RequestParam(value="method", required=false, defaultValue="asPoliteForm") String method,
            @RequestParam(value="message", required=false, defaultValue="") String message,
            @RequestParam(value="word", required=false) String word,
            @RequestParam(value="answer", required=false) String answer,
            Model model) {
        if (answer != null) {
            Verb v = new Verb(word);
            String s = v.asPoliteForm();
            if (s.equals(answer)) {
                model.addAttribute("message", "Correct: " + word + " = " + s);
            } else {
                model.addAttribute("message", "Wrong: " + word + " should be " + s + " NOT " + answer);
            }
        }
        model.addAttribute("clazz", clazz);
        model.addAttribute("method", method);
        model.addAttribute("word", word);
        return "grammar";
    }
}
