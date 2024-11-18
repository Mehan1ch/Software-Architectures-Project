<?php

namespace App\Http\Controllers;

use App\Http\Requests\Store\StoreCategoryRequest;
use App\Http\Requests\Update\UpdateCategoryRequest;
use App\Http\Resources\CategoryResource;
use App\Http\Resources\Collections\CategoryCollection;
use App\Models\Category;
use Illuminate\Http\JsonResponse;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(): CategoryCollection
    {
        return new CategoryCollection(Category::paginate());
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreCategoryRequest $request): CategoryResource
    {
        $category = Category::create($request->validated());
        return new CategoryResource($category);
    }

    /**
     * Display the specified resource.
     */
    public function show(Category $category): CategoryResource
    {
        return new CategoryResource($category);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateCategoryRequest $request, Category $category): CategoryResource
    {
        $category->update($request->validated());
        return new CategoryResource($category);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Category $category): JsonResponse
    {
        $category->delete();
        return response()->json(['message' => 'Category deleted successfully.'], 200);
    }
}
