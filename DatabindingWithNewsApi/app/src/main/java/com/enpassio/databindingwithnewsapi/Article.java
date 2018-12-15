package com.enpassio.databindingwithnewsapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private String source;
    private String author;
    private String title;
    private String description;
    private String imageUrl;
    private String publishingDate;
    private String content;

    public Article(String source, String author, String title, String description, String imageUrl, String publishingDate, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public String getContent() {
        return content;
    }

    protected Article(Parcel in) {
        source = in.readString();
        author = in.readString();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        publishingDate = in.readString();
        content = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(source);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(publishingDate);
        dest.writeString(content);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public String toString() {
        return "Article{" +
                "source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishingDate='" + publishingDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
