<?php

namespace App\Http\Requests\Store;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class StoreWorkRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, ValidationRule|array|string>
     */
    public function rules(): array
    {
        return [
            'title' => 'required|string|max:100',
            'content' => 'required|string',
            'moderator_id' => 'uuid|exists:users,id',
            'rating_id' => 'uuid|exists:ratings,id',
            'language_id' => 'uuid|exists:languages,id',
            'warnings' => 'array',
            'warnings.*' => 'uuid|exists:warnings,id',
            'tags' => 'array',
            'tags.*' => 'uuid|exists:tags,id',
            'characters' => 'array',
            'characters.*' => 'uuid|exists:characters,id',
            'categories' => 'array',
            'categories.*' => 'uuid|exists:categories,id',
        ];
    }
}
