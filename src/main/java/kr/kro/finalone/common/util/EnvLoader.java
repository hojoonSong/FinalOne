package kr.kro.finalone.common.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.util.Set;

public class EnvLoader {
    private static final Dotenv dotenv = loadDotenv();

    private static Dotenv loadDotenv() {
        try {
            return Dotenv.configure().directory(".").ignoreIfMissing().load();
        } catch (Exception e) {
            throw new IllegalStateException(".env 파일 로드 실패", e);
        }
    }

    /**
     * 주어진 키에 해당하는 환경 변수의 값을 반환합니다.
     *
     * @param key 환경 변수의 키.
     * @return 해당하는 환경 변수의 값이 있으면 반환, 없으면 null 반환
     */
    public static String getProperty(String key) {
        return dotenv.get(key);
    }

    /**
     * 주어진 키에 해당하는 환경 변수의 값을 반환하거나, 없으면 기본값을 반환합니다.
     *
     * @param key 환경 변수의 키.
     * @param defaultValue 해당 환경 변수가 없을 때 반환할 기본값
     * @return 해당하는 환경 변수의 값이 있으면 반환, 없으면 기본값 반환
     */
    public static String getPropertyOrDefault(String key, String defaultValue) {
        return dotenv.get(key) != null ? dotenv.get(key) : defaultValue;
    }

    /**
     * 모든 환경 변수를 반환합니다.
     *
     * @return 환경 변수들의 키-값 쌍을 담은 Map
     */
    public static Set<DotenvEntry> getAllProperties() {
        return dotenv.entries();
    }
}
