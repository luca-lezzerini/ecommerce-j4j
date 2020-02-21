import { LoginResponseDto } from './login-response-dto';

export class TagliaSearchDto extends LoginResponseDto {
  searchKey: string;
  page: number;
}
