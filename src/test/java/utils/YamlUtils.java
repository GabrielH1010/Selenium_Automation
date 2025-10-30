package utils;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class YamlUtils {

    private static Map<String, Object> yamlMap;

    static {
        try (InputStream arquivo = YamlUtils.class.getClassLoader().getResourceAsStream("env.yaml")) {
            if (arquivo == null) {
                throw new RuntimeException("Arquivo YAML não encontrado!");
            }
            Yaml yaml = new Yaml();
            yamlMap = yaml.load(arquivo);
        } catch (Exception erro) {
            throw new RuntimeException("Erro ao carregar YAML: " + erro.getMessage(), erro);
        }
    }

    public static String getValorAmbiente(String path) {
        String[] keys = path.split("\\.");
        Object valorAtual = yamlMap;

        for (String key : keys) {
            if (valorAtual instanceof Map<?, ?> map) {
                valorAtual = map.get(key);
            } else {
                throw new RuntimeException("Caminho inválido no YAML: " + path);
            }
        }

        if (valorAtual instanceof String valorFinal) {
            return valorFinal;
        } else {
            throw new RuntimeException("Valor do YAML não é uma string: " + path);
        }
    }
}