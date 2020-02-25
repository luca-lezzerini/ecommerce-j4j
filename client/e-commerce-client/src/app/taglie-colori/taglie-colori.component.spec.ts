import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaglieColoriComponent } from './taglie-colori.component';

describe('TaglieColoriComponent', () => {
  let component: TaglieColoriComponent;
  let fixture: ComponentFixture<TaglieColoriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaglieColoriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaglieColoriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
