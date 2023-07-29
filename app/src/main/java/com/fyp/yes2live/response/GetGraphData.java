package com.fyp.yes2live.response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GetGraphData implements List<GetGraphData> {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private List<GetGraphDataPayload> getGraphData;

    public GetGraphData(Integer code, String status, String message, List<GetGraphDataPayload> getGraphData) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.getGraphData = getGraphData;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GetGraphDataPayload> getGetGraphData() {
        return getGraphData;
    }

    public void setGetGraphData(List<GetGraphDataPayload> getGraphData) {
        this.getGraphData = getGraphData;
    }

    @Override
    public int size() {
        return getGraphData.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<GetGraphData> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        return null;
    }

    @Override
    public boolean add(GetGraphData getGraphData) {
        return false;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends GetGraphData> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends GetGraphData> collection) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public GetGraphData get(int i) {
        return null;
    }

    @Override
    public GetGraphData set(int i, GetGraphData getGraphData) {
        return null;
    }

    @Override
    public void add(int i, GetGraphData getGraphData) {

    }

    @Override
    public GetGraphData remove(int i) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<GetGraphData> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<GetGraphData> listIterator(int i) {
        return null;
    }

    @NonNull
    @Override
    public List<GetGraphData> subList(int i, int i1) {
        return null;
    }
}
