package mvc.results;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import play.Play;
import play.mvc.Http;
import play.mvc.results.Result;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by claudio on 26-01-15.
 */
public class JxlsResult extends Result {
    private Map<String, Object> model;
    private String archivo;

    public JxlsResult(Map<String, Object> model, String file) {
        this.model = model;
        this.archivo = file;
    }

    @Override
    public void apply(Http.Request request, Http.Response response) {
        try {
            doApply(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doApply(Http.Response response) throws Exception {
        final Http.Request theRequest = Http.Request.current();

        String template = Play.applicationPath + "/app/views/" + theRequest.controller + "/" + theRequest.actionMethod + ".xls";

        InputStream in = new FileInputStream(template);

        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = transformer.transformXLS(in, model);

        response.contentType = "application/vnd.ms-excel";
        response.setHeader("Content-Disposition", "attachment; filename=\""+ archivo +".xls\"");
        workbook.write(response.out);
    }
}
