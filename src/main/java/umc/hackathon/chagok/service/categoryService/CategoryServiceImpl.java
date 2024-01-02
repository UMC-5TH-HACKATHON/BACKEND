package umc.hackathon.chagok.service.categoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.hackathon.chagok.apiPayload.code.status.ErrorStatus;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;
import umc.hackathon.chagok.entity.Category;
import umc.hackathon.chagok.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> {
                    throw new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND);
                }
        );
    }
}
