package umc.hackathon.chagok.service.categoryService;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.hackathon.chagok.apiPayload.code.status.ErrorStatus;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Category;
import umc.hackathon.chagok.entity.Member;
import umc.hackathon.chagok.repository.CategoryRepository;
import umc.hackathon.chagok.service.MemberService.MemberService;
import umc.hackathon.chagok.web.dto.CategoryRequest;
import umc.hackathon.chagok.web.dto.CategoryResponse;
import umc.hackathon.chagok.web.dto.CategoryResponse.CategoryDTO;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    @Override
    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> {
                    throw new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND);
                }
        );
    }
    @Override
    public List<Category> findAllCategory(){
        return  categoryRepository.findAll();
    }

    public static CategoryResponse.CategoryDTO toCategoryDTO(Category category) {
        return CategoryResponse.CategoryDTO.builder()
                .id(category.getId())
                .name(category.getCategoryName())
                .build();
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = findAllCategory();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category c : categories) {
            categoryDTOS.add(toCategoryDTO(c));
        }
        return categoryDTOS;
    }

    @Transactional
    public Category createCategory(CategoryRequest.CreateCategoryDto request) {
        Category newCategory = Category.builder()
                .categoryName(request.getName())
                .build();

        categoryRepository.save(newCategory);

        return newCategory;
    }
}
