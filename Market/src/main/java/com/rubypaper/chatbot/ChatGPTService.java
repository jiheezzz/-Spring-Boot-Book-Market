package com.rubypaper.chatbot;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rubypaper.book.BookService;
import com.rubypaper.book.BookVO;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final BookService bookService;

    public ChatGPTService(RestTemplate restTemplate, BookService bookService) {
        this.restTemplate = restTemplate;
        this.bookService = bookService;
    }
    
    private String extractMessageFromResponse(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode messageNode = root.path("choices").get(0).path("message").path("content");
            return messageNode.asText().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "추천을 가져오는 중 오류가 발생했습니다.";
        }
    }

    public String getBookRecommendations(String query) {
        List<BookVO> recommendedBooks = bookService.findByBookTitleContaining(query);
        if (recommendedBooks.isEmpty()) {
            return "관련 책을 찾을 수 없습니다.";
        }

        StringBuilder booksList = new StringBuilder("다음은 사용자가 입력한 키워드에 맞는 책 목록입니다:\n\n");
        for (BookVO book : recommendedBooks) {
            booksList.append(book.getWriter_name())
                    .append(" 의 ")
                    .append(book.getBook_name())
                    .append("을 추천드려요.")
                    .append(book.getBook_title())
                    .append(" (")
                    .append(book.getBook_etc())
                    .append(")\n")
                    .append("가격: ").append(book.getBook_price()).append("원\n")
                    .append("===> <a href=\"/book/edit?book_idx=")
                    .append(book.getBook_idx())
                    .append("\">구매하기</a>\n\n");
        }

        // JSON 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("model", "gpt-3.5-turbo");
        ArrayNode messagesArray = objectMapper.createArrayNode();

        ObjectNode systemMessageNode = objectMapper.createObjectNode();
        systemMessageNode.put("role", "system");
        systemMessageNode.put("content", "You are a helpful assistant with expertise in book recommendations."
        		+ " 구매링크는 ===> 다음 바로 하이퍼링크형태로 넣어줘 "
        		+ "그리고 가격과 링크는 필수로 알려줘 ");
        messagesArray.add(systemMessageNode);

        ObjectNode userMessageNode = objectMapper.createObjectNode();
        userMessageNode.put("role", "user");
        userMessageNode.put("content", booksList.toString());
        messagesArray.add(userMessageNode);

        rootNode.set("messages", messagesArray);
        rootNode.put("max_tokens", 300);

        // JSON 문자열로 변환
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
            return "JSON 형식 변환 중 오류가 발생했습니다.";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange("https://api.openai.com/v1/chat/completions",
        		HttpMethod.POST, entity, String.class);

        return extractMessageFromResponse(response.getBody());
    }
}