package com.njwpdi.common.utils;

import com.njwpdi.common.eneity.TreeEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
public class FileUtil {

    /**
     * 获取该目录下的文件名及其路径，返回一个树形结构的数据
     *
     * @param tree
     * @param filepath
     * @return
     * @throws FileNotFoundException
     */
    public static TreeEntity getFileToTree(TreeEntity tree, String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        List<TreeEntity> childrenList = new ArrayList<TreeEntity>();
        if (file.isFile()) {
            tree.setChildren(childrenList);
        }
        if (file.isDirectory()) {
            // 获取目录下的所有文件
            String[] list2 = file.list();
            for (int i = 0; i < list2.length; i++) {
                String fileName = list2[i];
                // 根据当前文件夹，拼接其下文文件形成新的路径
                String filePath = file.getPath() + "/" + fileName;
                TreeEntity tree1 = new TreeEntity(fileName, filePath, childrenList);
                // 这里是排除macos下自动生成的.DS_STORE文件
                if (!fileName.startsWith(".")) {
                    childrenList.add(tree1);
                }
                // 递归
                getFileToTree(tree1, filePath);
            }
            tree.setChildren(childrenList);
        }
        return tree;

    }

}
