package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;


public class PageGenerator {
    public static String getPage(String filename) {
        Configuration cfg = new Configuration();
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate("public_html" + File.separator + filename);
            template.dump(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

}
