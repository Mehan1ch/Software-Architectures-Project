<?php

namespace App\Http\Requests\Update;

use App\Rules\MorphIdRule;
use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class UpdateCommentRequest extends FormRequest
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
            // TODO Check if this works
            "commentable_type" => ['required', 'string', Rule::in(['App\Models\Collection', 'App\Models\Work'])],
            "commentable_id" => ['required', 'uuid', new MorphIdRule],
        ];
    }
}
