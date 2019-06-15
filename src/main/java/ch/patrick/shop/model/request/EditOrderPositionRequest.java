package ch.patrick.shop.model.request;

import ch.patrick.shop.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Anfrage für eine Bearbeitung der Bestellungsposition
 *
 * @author Patrick
 * @version 1.0
 */
public class EditOrderPositionRequest {

    /**
     * Index Nummer der Position
     */

    private Integer index;

    /**
     * Artikel der Position
     */

    private Article article;

    /**
     * ausgewählte Inhalte der Position
     */

    private List<Long> selectedContents = new ArrayList<>();

    public EditOrderPositionRequest() {
    }

    public EditOrderPositionRequest(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Long> getSelectedContents() {
        return selectedContents;
    }

    public void setSelectedContents(List<Long> selectedContents) {
        this.selectedContents = selectedContents;
    }
}
