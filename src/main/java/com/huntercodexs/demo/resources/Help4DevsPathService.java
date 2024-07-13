package com.huntercodexs.demo.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsPathService {

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">sanitizePath</h6>
     *
     * <p style="color: #CDCDCD">Sanitize any path as you need</p>
     *
     * @param path (String)
     * @return String (Path sanitized)
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "") + "/";
    }

}
