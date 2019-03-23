package com.shield.frame.sysmng.qvo;

import java.util.HashMap;

import com.shield.frame.common.qvo.TreeNode;

public class MenuTreeNode extends TreeNode<HashMap<String, String>> {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}