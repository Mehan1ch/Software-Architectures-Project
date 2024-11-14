<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class StoreCommentRequest extends FormRequest
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
            "content" => "required|string",
            "user_id" => "required|uuid|exists:users,id",
            // TODO Implement own Validation for Morphs
            "commentable_type" => "required|morph_exists:commentable_type",
            "commentable_id" => "required|morph_exists:commentable_id",
        ];
    }
}
