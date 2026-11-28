from pydantic import BaseModel, Field


class EvaluationRequest(BaseModel):
    question: str
    answer: str
    interviewType: str
    difficulty: str
    topic: str | None = None


class EvaluationResponse(BaseModel):
    correctness: float = Field(ge=0, le=10)
    relevance: float = Field(ge=0, le=10)
    completeness: float = Field(ge=0, le=10)
    clarity: float = Field(ge=0, le=10)
    score: float = Field(ge=0, le=10)
    feedback: str
    improvementSuggestion: str