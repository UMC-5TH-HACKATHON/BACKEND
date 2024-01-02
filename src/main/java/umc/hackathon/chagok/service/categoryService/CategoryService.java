package umc.hackathon.chagok.service.categoryService;

import java.util.List;
import umc.hackathon.chagok.entity.Category;
import umc.hackathon.chagok.web.dto.CategoryRequest;
import umc.hackathon.chagok.web.dto.CategoryResponse.CategoryDTO;

public interface CategoryService {


    public Category createCategory(CategoryRequest.CreateCategoryDto request);
    public Category findCategory(Long categoryId);

    public List<Category> findAllCategory();

    public List<CategoryDTO> getCategories();
}
