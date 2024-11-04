<?php

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
        Schema::create('work_characters', function (Blueprint $table) {
            $table->uuid('id')->primary();
            $table->foreignUuid('work_id')->constrained('works');
            $table->foreignUuid('character_id')->constrained('characters');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('work_characters');
    }
};
