package com.yunjin.api_backed.algorithm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: TextSimilarityUtil
 * @Version: 1.0
 * @Description: 文本相似度计算工具类
 */

public class TextSimilarityUtil {
    private static final boolean DEBUG = true;  // 全局调试开关

    public static double computeTextSimilarity(String text1, String text2) throws Exception {
        // 获取 Python 脚本的绝对路径
        String pythonScriptPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", "linyi", "check", "algorithm", "py", "bert.py").toString();

        // 调试信息：显示完整命令
        if (DEBUG) {
            System.out.println("[DEBUG] Python脚本路径: " + pythonScriptPath);
            System.out.println("[DEBUG] Python执行路径: D:\\ProgramData\\Anaconda3\\envs\\NLP\\python.exe");
            System.out.println("[DEBUG] 文本1长度: " + text1.length());
            System.out.println("[DEBUG] 文本2长度: " + text2.length());
        }

        // 创建 ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder(
                "D:\\ProgramData\\Anaconda3\\envs\\NLP\\python.exe",
                pythonScriptPath,
                text1,
                text2);

        // 合并错误流和输出流，方便调试
        processBuilder.redirectErrorStream(true);

        // 添加环境变量（如果需要）
        // processBuilder.environment().put("PYTHONPATH", "...");

        long startTime = System.currentTimeMillis();
        if (DEBUG) {
            System.out.println("[DEBUG] 开始执行Python脚本: " + new java.util.Date());
        }

        Process process = null;
        try {
            process = processBuilder.start();

            // 获取输出流
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            // 读取输出
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                if (DEBUG) {
                    System.out.println("[PYTHON] " + line);  // 实时输出Python脚本的打印
                }
            }

            // 等待进程结束（带超时）
            boolean finished = process.waitFor(30, TimeUnit.SECONDS);  // 30秒超时
            if (!finished) {
                throw new RuntimeException("Python脚本执行超时（30秒）");
            }

            // 检查退出码
            int exitCode = process.exitValue();
            if (exitCode != 0) {
                throw new RuntimeException("Python脚本非正常退出，退出码: " + exitCode + "\n输出:\n" + output);
            }

            if (DEBUG) {
                System.out.println("[DEBUG] Python脚本执行完成，耗时: " + (System.currentTimeMillis() - startTime) + "ms");
                System.out.println("[DEBUG] 完整输出:\n" + output);
            }

            // 提取相似度
            Pattern pattern = Pattern.compile("文本相似度:\\s*(\\d+(\\.\\d+)?)");
            Matcher matcher = pattern.matcher(output.toString());
            if (matcher.find()) {
                double similarity = Double.parseDouble(matcher.group(1));
                if (DEBUG) {
                    System.out.println("[DEBUG] 解析到的相似度: " + similarity);
                }
                return similarity;
            } else {
                throw new RuntimeException("无法从输出中解析相似度:\n" + output);
            }
        } catch (Exception e) {
            if (process != null) {
                process.destroyForcibly();  // 确保进程被终止
            }
            throw new RuntimeException("执行Python脚本时出错: " + e.getMessage(), e);
        }
    }

    public static double sim(String text1, String text2) {
        try {
            if (DEBUG) {
                System.out.println("[DEBUG] 开始计算文本相似度");
                System.out.println("[DEBUG] 文本1(前100字符): " + (text1.length() > 100 ? text1.substring(0, 100) + "..." : text1));
                System.out.println("[DEBUG] 文本2(前100字符): " + (text2.length() > 100 ? text2.substring(0, 100) + "..." : text2));
            }

            double similarity = computeTextSimilarity(cleanResume(text1), cleanResume(text2));

            if (DEBUG) {
                System.out.println("[DEBUG] 最终相似度结果: " + similarity);
            }
            return similarity;
        } catch (Exception e) {
            System.err.println("[ERROR] 计算相似度失败: " + e.getMessage());
            e.printStackTrace();
            return 0.0;  // 失败时返回默认值
        }
    }

    // 文本清洗方法（示例）
    private static String cleanResume(String text) {
        if (text == null) return "";
        // 这里可以添加你的文本清洗逻辑
        return text.trim();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String text1 = "这是第一个文本";
        String text2 = "这是第二个文本";

        try {
            System.out.println("==== 测试开始 ====");
            double similarity = sim(text1, text2);
            System.out.println("==== 测试结果 ====");
            System.out.println("总耗时: " + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("相似度: " + similarity);
        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}