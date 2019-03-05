package com.enpassio.databindingwithnewsapi.model;

public class Article {

    private final String source;
    private final String author;
    private final String title;
    private final String description;
    private final String articleUrl;
    private final String imageUrl;
    private final String publishingDate;
    private final String content;

    public Article(String source, String author, String title, String description, String articleUrl, String imageUrl, String publishingDate, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
        this.publishingDate = publishingDate;
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishingDate='" + publishingDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
