<?php

use App\Enums\ModerationEnum;
use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('works', function (Blueprint $table) {
            $table->uuid('id')->primary();
            $table->string('title');
            $table->text('content');
            $table->foreignUuid('user_id')->constrained('users');
            $table->enum('moderation_status', ModerationEnum::values())->default(ModerationEnum::PENDING);
            $table->foreignUuid('moderator_id')->nullable()->constrained('users');
            $table->foreignUuid('rating_id')->nullable()->constrained('ratings');
            $table->foreignUuid('language_id')->nullable()->constrained('languages');
            $table->timestamps(); // Adds `created_at` and `updated_at` columns
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('works');
    }
};
